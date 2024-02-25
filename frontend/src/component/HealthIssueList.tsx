import React, { useState } from 'react';
import HealthIssue from './HealthIssue.tsx';
import axios from 'axios';

interface HealthIssueListProps {
    issues: { id: string, name: string }[];
    selectedIssues: string[];
    onIssueClick: (issueId: string) => void;
    fetchHealthIssues: () => void; // Function to fetch issues from backend
    onRemove: (name: string) => void; // Function to remove an issue
}

const HealthIssueList: React.FC<HealthIssueListProps> = ({ issues = [], selectedIssues = [], onIssueClick, fetchHealthIssues }) => {
    const [searchTerm, setSearchTerm] = useState('');
    const [startIndex, setStartIndex] = useState(0);

    const filteredIssues = issues.filter(issue =>
        issue.name.toLowerCase().startsWith(searchTerm.toLowerCase())
    );

    const handleMoreClick = () => {
        setStartIndex(startIndex + 7);
    };

    const handleBackClick = () => {
        setStartIndex(Math.max(0, startIndex - 7));
    };

    const handleIssueClick = (issueId: string) => {
        onIssueClick(issueId);
    };

    const handleAddIssue = async (searchTerm: string) => {
        try {
            await axios.post('/api/health-tracker/add-health-issue', { name: searchTerm });
            fetchHealthIssues(); // Fetch updated issues from backend
            setSearchTerm(''); // Reset the search term
        } catch (error) {
            console.error('Error adding health issue:', error);
        }
    };

    const handleRemoveIssue = async (name: string) => {
        try {
            await axios.delete(`/api/health-tracker/remove-health-issue/${name}`, {
                params: { name: name } // Use params instead of data for DELETE request
            });
            fetchHealthIssues(); // Fetch updated issues from backend
        } catch (error) {
            console.error('Error removing health issue:', error);
        }
    }

    return (
        <div className="health-issues">
            <input
                type="text"
                className="health-issues-input"
                placeholder="Search..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
            />
            {filteredIssues.slice(startIndex, startIndex + 7).map((issue) => (
                <HealthIssue
                    key={issue.id}
                    name={issue.name}
                    onClick={() => handleIssueClick(issue.id)}
                    onRemove={() => handleRemoveIssue(issue.name)} // Passing issue name to handleRemoveIssue
                    selected={selectedIssues.includes(issue.id)}
                />
            ))}
            <div className="button-container">
                {filteredIssues.length === 0 && (
                    <button onClick={() => handleAddIssue(searchTerm)} className="add-button">
                        <img src="/public/add.png" alt="Add" className="add-icon"/></button>
                )}
            </div>
            {startIndex > 0 && <button onClick={handleBackClick}>Back</button>}
            {startIndex + 7 < filteredIssues.length && (
                <button onClick={handleMoreClick}>More</button>
            )}
        </div>
    );
};

export default HealthIssueList;