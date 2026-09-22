# Privacy Policy

Kawach_Cloud is designed to encrypt files locally before they are uploaded to Telegram Saved Messages. This application is not a central file-storage service and does not operate its own cloud server for user files.

## What Kawach_Cloud does
- encrypts selected file content locally before upload
- uses Telegram as the intended storage backend
- stores metadata locally in the app and returns to the Telegram account as appropriate
- supports local app-level search and file management

## What Kawach_Cloud does not do
- does not create a separate developer-managed file-storage backend
- does not store hardcoded encryption keys in source control
- does not send OTPs, passwords, Telegram session data, or decryption keys to a developer backend
- does not claim absolute privacy guarantees beyond what the implementation can support

## Telegram metadata
Telegram account metadata, cloud storage metadata, and traffic information remain subject to Telegram's systems and policies. Kawach_Cloud can reduce plaintext exposure in app-layer data, but Telegram can still see Telegram-account-level metadata available to the platform.

## Key responsibility
Users are responsible for preserving their local encryption secrets. If encryption credentials are lost, encrypted data may be unrecoverable.
