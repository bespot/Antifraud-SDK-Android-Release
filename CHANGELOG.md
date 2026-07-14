# Changelog

All notable changes to the Bespot Gatekeeper Android SDK are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.2.0] - 2026-07-14

### Added

- **Access token authentication** alongside existing OAuth 2.0 flow
- `SafeSdk.initWithAccessToken(apiBaseUrl, apiKey, token)` — initialize the network layer with a bearer token supplied by your app
- `SafeSdk.setAccessToken(token)` — update the bearer token without re-registering the device or restarting periodic checks
- New failure types: `Failure.InvalidToken`, `Failure.AuthError`, `Failure.AlreadyInitialized`
- Client-side access token validation (blank/whitespace token) in the shared SDK layer

### Changed

- Updated network contract to `0.9.5-SNAPSHOT`
- Upgraded to **Java 21** and **Ktor 3**
- `subscribe` uses `FraudulentCheckObserver` (same callback as `check`)

## [1.0.2] - 2026-05-15

### Changed

- Updated **TM-Apps/konnection** dependency from **1.4.0** to **1.4.2**.

## [1.0.1] - 2026-05-12

### Added

- **Backend-driven periodic check interval**: The SDK reads optional `configuration.periodic_interval` from the `/register` response when the backend includes it.

### Changed

- Updated the **Rootbeer** library dependency from **0.1.0** to **0.1.2**.
