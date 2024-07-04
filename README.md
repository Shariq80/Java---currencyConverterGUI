# Currency Converter

This is a simple Java Swing application that converts currency using exchange rates fetched from the ExchangeRate-API.

## Features

- Convert between USD, EUR, GBP, INR, SAR.
- Fetch real-time exchange rates from the ExchangeRate-API.
- Simple and user-friendly GUI.

## Prerequisites

- Java Development Kit (JDK) 8 or later
- Apache Maven

## Setup and Installation

### Step 1: Clone the Repository

```sh
git clone https://github.com/your-username/CurrencyConverter.git
```

### Step 2: Configure the API Key
 1. Sign up at ExchangeRate-API to get your API key.
 2. Open `app.java` file located in `src\main\java\com\example`.
 3. Replace `YOUR_API_KEY` with your actual API key in the `API_URL` constant.

### Stem 3: Build the Project
```bash
mvn clean install
```

### Step 4: Run the Application
```bash
mvn exec:java -Dexec.mainClass = "com.example.app"
```

## Usage
 1. Enter the amount you want to convert.
 2. Select the currency you are converting from and to.
 3. Click the "Convert" button.
 4. The result will be displayed below the button.

## Dependencies
 - Apache HttpClient
 - org.json