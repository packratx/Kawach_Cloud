# Kawach_Cloud

Private encrypted cloud storage using Telegram.

Developer:
Aravind(guru)
Email: darkwebaccess404@gmail.com

## Purpose
Kawach_Cloud is an open-source Android application designed to give users a privacy-focused cloud-storage experience by storing encrypted data through Telegram Saved Messages as the primary backend. The app is intended to keep user data encrypted locally before it is uploaded, without requiring a central custom storage server.

## Architecture
- Kotlin + Android + Jetpack Compose
- Material 3 UI with dark/light/system theme support
- MVVM-inspired screen structure with layered data and domain responsibilities
- Telegram API integration through TDLib for authorized user account access
- Local encryption and metadata handling before remote storage

## Setup
1. Install Java 17.
2. Install Android Studio or Android SDK command-line tools.
3. Configure local.properties with your Android SDK path if needed.
4. Ensure your Telegram API credentials are set using local.properties or environment variables rather than committing secrets to Git.
5. Open the project in Android Studio or run Gradle from the project root.

## Telegram API requirements
TDLib integration requires valid Telegram API credentials for your own app ID and hash. These must not be committed to the repository. Keep them in local.properties or another secure local mechanism and ensure they are not logged or exposed.

## Build instructions
Use the project Gradle wrapper when available:

./gradlew assembleDebug

Optional validation commands:

./gradlew test
./gradlew lint

## Encryption architecture
The app is designed to encrypt files before upload and decrypt them after download. The implementation should use well-known Android/JVM cryptography primitives such as AES-GCM with strong key handling and nonces. Key material must never be hardcoded or sent to a developer server.

## Security limitations
This app is designed for application-layer privacy, not absolute anonymity. Telegram still controls Telegram account metadata and cloud infrastructure. The developer cannot recover lost encryption credentials. Users are responsible for preserving their encryption secrets.

## Contribution
Contributions are welcome. Please open an issue or pull request with a clear explanation of the change and any relevant security considerations.
