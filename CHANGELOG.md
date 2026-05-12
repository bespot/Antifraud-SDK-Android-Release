# Changelog

All notable changes to the Bespot Gatekeeper Android SDK are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.1] - 2026-05-12

### Added

- **Backend-driven periodic check interval**: The SDK reads optional `configuration.periodic_interval` from the `/register` response when the backend includes it.

### Changed

- Updated the **Rootbeer** library dependency from **0.1.0** to **0.1.2**.
