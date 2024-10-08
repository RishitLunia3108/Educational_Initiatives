# Smart Office Facility Management System

## Overview

The Smart Office Facility Management System is a console-based application designed to manage conference room bookings, occupancy detection, and automate control of air conditioning and lighting based on room occupancy. The system is implemented using various design patterns to demonstrate software engineering best practices, including the Singleton, Observer, and Command design patterns.

## Features

- **Room Configuration:** Set up the number of conference rooms available in the office.
- **Room Booking:** Book and cancel bookings for conference rooms.
- **Occupancy Detection:** Detect room occupancy through sensors that register when at least two people enter a room.
- **Automatic Room Release:** Automatically release bookings for unoccupied rooms after 5 minutes.
- **Light and AC Control:** Automatically turn off lights and air conditioning when the room is not occupied.

## Design Patterns Used

1. **Singleton Pattern:** 
   - Ensures that the office configuration and room booking system have only one instance throughout the application.

2. **Observer Pattern:**
   - Implements sensors and control systems (lights, AC) as observers to the room's occupancy status.

3. **Command Pattern:**
   - Handles booking, cancellation, and room status updates through commands, allowing for flexible and extendable operations.

## Getting Started

### Prerequisites

- JAVA
- Any code editor or IDE (like VSCode, etc.)

### Installation

1. Clone this repository:
   ```bash
   git clone https://github.com/RishitLunia3108/Educational_Initiatives.git
   cd smart-office-facility-management
