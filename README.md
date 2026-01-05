# TedLassoBizCard ⚽️

A sleek, data-driven Business Card and Portfolio application built using **Android Jetpack Compose**. This project demonstrates modern UI development, state management, and asset handling in Android.

## Preview
The app features a professional profile card for **Ted Lasso**, including a profile picture, bio, and an expandable portfolio section that reveals coaching history dynamically.

| Open App | Portfolio with 2 Rows | Portfolio with more Rows |
| :---: | :---: | :---: |
| <img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/12f61f5b-84e4-45b1-a651-63a02f966321" /> | <img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/f1fb26be-8353-4326-a309-6a1cf1593921" /> | <img width="1080" height="2400" alt="image" src="https://github.com/user-attachments/assets/c4593ae9-cd0b-470c-88b9-5926ef6a8a01" /> |

## Key Features
* **Jetpack Compose & Material 3:** Fully declarative UI using the latest Android design standards.
* **State-Driven UI:** Uses `mutableStateOf` and `remember` to toggle the visibility of the portfolio section.
* **Asset-Based Data Loading:** Loads project information (titles and descriptions) from a local `assets.json` file.
* **GSON Integration:** Seamlessly parses JSON data into Kotlin objects.
* **Edge-to-Edge:** Implements `enableEdgeToEdge()` and `WindowInsets` for a modern, immersive user experience.

## Training & Credits
This project was developed as part of the learning journey in the **[Jetpack Compose Masterclass](https://www.udemy.com/course/kotling-android-jetpack-compose-/?couponCode=CP250105G1)** on Udemy. 

The course provides comprehensive training on:
* Building high-quality Android apps with **Kotlin**.
* Mastering **Declarative UI** patterns.
* Handling state, themes, and complex layouts in Compose.

## Tech Stack
* **Language:** [Kotlin](https://kotlinlang.org/)
* **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose)
* **Design:** Material Design 3
* **JSON Parsing:** [GSON](https://github.com/google/gson)
* **Architecture:** Composable Function Components

## Setup & Installation
1. Clone the repo
2. Open in Android Studio: Ensure you have the latest version of Flamingo or Hedgehog+.
3. Sync Gradle: Allow the IDE to download necessary dependencies.
4. Run: Click the 'Run' button to see the app on an emulator or physical device.
