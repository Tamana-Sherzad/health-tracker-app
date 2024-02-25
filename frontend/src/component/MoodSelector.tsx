import React, { useState } from 'react';

const MoodSelector: React.FC<{ onMoodSelect: (selectedMood: string) => void }> = ({ onMoodSelect }) => {
    const [selectedMood, setSelectedMood] = useState<string>('');

    const handleMoodClick = (mood: string) => {
        setSelectedMood(mood);
        onMoodSelect(mood);
    };

    return (
        <div className="mood-selector">
            <h2>How are you feeling today?</h2>
            <button
                className={`mood-button ${selectedMood === 'happy' ? 'selected' : ''}`}
                onClick={() => handleMoodClick('happy')}
            >
                😊
            </button>
            <button
                className={`mood-button ${selectedMood === 'neutral' ? 'selected' : ''}`}
                onClick={() => handleMoodClick('neutral')}
            >
                😐
            </button>
            <button
                className={`mood-button ${selectedMood === 'sad' ? 'selected' : ''}`}
                onClick={() => handleMoodClick('sad')}
            >
                😢
            </button>
        </div>
    );
};

export default MoodSelector;
