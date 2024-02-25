import React from 'react';

const WelcomeText: React.FC = () => {
    return (
        <div className="welcome-text">
            <div className="welcome-heading">
            </div>
            <div className="welcome-message">
                <p>Your effortless ally in prioritizing your health and well-being, even amidst your busiest
                    days!</p>
                <p>Crafted for simplicity, our platform offers:</p>
                <ul>
                    <li>Effortless tracking of your fitness progress</li>
                    <li>Managing medical records</li>
                    <li>Staying informed about your health</li>
                </ul>
                <p>We understand the demands of a busy lifestyle, yet we believe in the importance of staying connected
                    to your health journey.</p>
                <p>With Health-Sync, you can stay on top of your health without added stress or time constraints.</p>
                <p>Start your journey to optimal health today, because even in the busiest moments, your health
                    matters!</p>
            </div>
        </div>
    );
};

export default WelcomeText;
