# Contributing to Kawach_Cloud

Thanks for helping build a privacy-focused Android cloud-storage app.

## Basics
- Keep changes focused and easy to review.
- Maintain separation between UI, domain, and data logic.
- Avoid adding unnecessary dependencies.
- Prefer stable, well-supported Android libraries.

## Security review
If you are making changes related to encryption, Telegram auth, secrets, or session handling:
- do not add backdoors
- do not log secrets or credentials
- do not commit keys, session files, or OTP values
- do not invent Telegram APIs or fake backend workflows

## Best practices
- use Kotlin and AndroidX idioms
- prefer Compose patterns and Material 3
- validate the app with the project build and tests when possible
- keep the repository compilable after each major phase of work

## Pull requests
Open a pull request with:
- a clear summary
- the problem being fixed or feature being added
- testing notes if applicable
- any security considerations or known limitations
