import React from 'react';
import trash from '/public/trash.png';


interface HealthIssueProps {
    name: string;
    onClick: () => void;
    onRemove: (name : string) => void;
    selected: boolean;
}

const HealthIssue: React.FC<HealthIssueProps> = ({ name, onClick, onRemove, selected }) => {
    return (
        <div className={`health-issue ${selected ? 'selected' : ''}`} onClick={onClick}>
            <span>{name}</span>
            <button className="remove-issue-button" onClick={(e) => {e.stopPropagation(); onRemove(name)}}>
                <img src={trash} alt="Remove" className="remove-icon" />
            </button>
        </div>
    );
};

export default HealthIssue;