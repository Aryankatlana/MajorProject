import React from 'react';

function Header() {
    return (
        <header className="header">
            <h1 className="logo">Medium</h1>
            <nav className="nav">
                <a href="#ourstory">Our story</a>
                <a href="#membership">Membership</a>
                <a href="#write">Write</a>
                <a href="#signin">Sign in</a>
                <button className="get-started-btn">Get started</button>
            </nav>
        </header>
    );
}

export default Header;