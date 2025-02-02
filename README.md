# My Android Assessment

## Overview
This is a simple Android app that fetches and displays country data from the [REST Countries API](https://restcountries.com/v3.1/all).
Users can search for a country, filter by population, and view basic details.

## Features
- Fetches country data from the API
- Displays country name, flag, capital, and population
- Search for countries by name
- Filter countries based on population
- Simple and clean UI with Jetpack Compose

## Tech Used
- **Jetpack Compose** for UI
- **MVVM Architecture**
- **Dagger-Hilt** for dependency injection
- **Retrofit** for API calls
- **Coroutines & Flow** for async tasks
- **Coil** for loading images

## Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/spiro4/MyAndroidAssesment.git
   ```
2. Open in **Android Studio**
3. Sync Gradle and build the project
4. Run on an emulator or a physical device

## API Used
- **Endpoint:** `https://restcountries.com/v3.1/all`
- **Example Response:**
  ```json
  {
    "name": { "common": "India" },
    "capital": ["New Delhi"],
    "population": 1366417754,
    "flags": { "png": "https://flagcdn.com/w320/in.png" }
  }
  ```

---
🔗 **GitHub Repository:** [MyAndroidAssessment](https://github.com/spiro4/MyAndroidAssesment)

