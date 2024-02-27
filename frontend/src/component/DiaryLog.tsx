import React, { useState } from 'react';

const DiaryLog: React.FC<{ onSubmit: (entry: string) => void }> = ({ onSubmit }) => {
    const [entry, setEntry] = useState<string>('');

    const handleSubmit = () => {
        if (entry.trim() !== '') {
            onSubmit(entry);
            setEntry('');
        }
    };

    return (
        <div className="diary-log">
            <h2>Diary Log</h2>
            <textarea
                className="diary-input"
                value={entry}
                onChange={(e) => setEntry(e.target.value)}
                placeholder="How was your day?..."
            ></textarea>
            <button onClick={handleSubmit}>Submit</button>
        </div>
    );
};

export default DiaryLog;
