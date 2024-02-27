import { BrowserRouter as Router } from 'react-router-dom';
import WelcomeText from "./component/WelcomeText.tsx";
import HealthIssueList from "./component/HealthIssueList.tsx";
import MoodSelector from "./component/MoodSelector.tsx";
import DiaryLog from "./component/DiaryLog.tsx";
import './index.css';
import axios from "axios";
import { useEffect, useState } from "react";


function App() {
    const [loggedIn, setLoggedIn] = useState(false);
    const [selectedIssues, setSelectedIssues] = useState<string[]>([]);
    const [mood, setMood] = useState<string>('');
    const [allIssues, setAllIssues] = useState<any[]>([]);
    const [error, setError] = useState<string>('');

    useEffect(() => {
        fetchHealthIssues();
    }, []);

    const fetchHealthIssues = async () => {
        try {
            const response = await axios.get('/api/health-tracker/health-issues');
            setAllIssues(response.data);
        } catch (error) {
            setError('Error fetching health issues');
        }
    };

    const handleLogout = () => {
        setLoggedIn(false);
        window.scrollTo(0, 0);
    };

    const handleMoodSelection = (selectedMood: string) => {
        setMood(selectedMood);
    };

    const handleDiarySubmit = (entry: string) => {
        console.log('Diary entry:', entry);
    };

    const handleLogin = () => {
        setLoggedIn(true);
    };

    const handleIssueClick = (issueId: string) => {
        // Toggle the selection of the clicked issue
        if (selectedIssues.includes(issueId)) {
            setSelectedIssues(selectedIssues.filter(id => id !== issueId));
        } else {
            setSelectedIssues([...selectedIssues, issueId]);
        }
    };

    return (
        <Router>
            <div className="container">
                <h1 className="app-name">Health-Sync</h1>
                {!loggedIn && <WelcomeText />}
                {loggedIn && (
                    <div className="dashboard">
                        {error && <div className="error-message">{error}</div>
                        }
                        <MoodSelector onMoodSelect={handleMoodSelection}/>
                        <DiaryLog onSubmit={handleDiarySubmit}/>
                        <div className="my-health-issues">
                            <h2>My Health Issues</h2>
                            {selectedIssues.map(issueId => (
                                <div key={issueId} className="health-issue selected">
                                    {allIssues.find(issue => issue.id === issueId)?.name}
                                </div>
                            ))}
                        </div>
                        <HealthIssueList
                            issues={allIssues}
                            selectedIssues={selectedIssues}
                            onIssueClick={handleIssueClick}
                            fetchHealthIssues={fetchHealthIssues}
                        />
                    </div>
                )
                }
                {
                    !loggedIn ? (
                        <button className="login-button" onClick={handleLogin}>Login</button>
                    ) : (
                        <button className="logout-button" onClick={handleLogout}>Logout</button>
                    )}
                {mood && <div className={`mood-background ${mood}`}></div>}
            </div>
        </Router>
    );
}

export default App;