# Antifraud SDK Android Release

[![VERSION](https://img.shields.io/badge/VERSION-0.4.8-green)](#)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat)](#)

Antifraud SDK is an easy to use Android library for protecting your application from fraudulent activities.
Our SDK requires minimal permissions and uses device information and sensors to detect and report potential threats,
so that app developers can make informed decisions about their application's actions.

## Features
|   Detection     	   | Supported  	 |          Description                    	           |
|:-------------------:|:------------:|:---------------------------------------------------:|
|    VPN        	     |   yes    	   |       Virtual Private Network              	        |
|  Mock Location   	  |   yes    	   | Another app is manipulating the device's location 	 |
| Multiple  Users  	  |   yes    	   |  More than one users are registered in the device	  |

## Installation

<img src="screenshots/sample_start.png" width="300" align="right" hspace="20">

Add the following lines inside the `dependencyResolutionManagement` block of your `settings.gradle.kts` 
(or inside the `repositories` block of the root `build.gradle.kts` file if you still use the old way)

```kotlin
dependencyResolutionManagement {
    maven(url = "https://artifactory.bespot.com/artifactory/bespot-antifraud")
    maven(url = "https://artifactory.bespot.com/artifactory/bespot-logger")
    maven(url = "https://jitpack.io" )
}
```
_The `bespot-logger` dependency is needed in order to have available logs for our mobile developers to make debugging easier,
and the `jitpack` dependency is needed for some dependencies our SDK has. We will try to remove them at a later date_

Next, add the following dependency inside your app's `build.gradle.kts` file
```kotlin
dependencies {
    implementation("com.bespot.antifraud:sdk-android:$latest_version")
}
```

Finally, you need to pass the `API_KEY` and `API_URL` as `resValues` inside your app's `build.gradle.kts` file:
```kotlin
resValue("string", "antifraud_sdk_key", YOUR_API_KEY)
resValue("string", "antifraud_sdk_api_url", YOUR_API_URL)
```
or via your app's `strings.xml` file
```xml
<string name="antifraud_sdk_key">YOUR_API_KEY</string>
<string name="antifraud_sdk_api_url"> YOUR_API_URL</string>
```
## Usage

<img src="screenshots/sample_subscribe.png" width="300" align="right" hspace="20">

The Antifraud SDK requires four permissions:
- [Fine Location](https://developer.android.com/reference/android/Manifest.permission#ACCESS_FINE_LOCATION)
- [Coarse Location](https://developer.android.com/reference/android/Manifest.permission#ACCESS_COARSE_LOCATION)
- [External Storage](https://developer.android.com/reference/android/Manifest.permission#WRITE_EXTERNAL_STORAGE) 
- [Audio Media](https://developer.android.com/reference/android/Manifest.permission#READ_MEDIA_AUDIO)

Our SDK exposes the following methods:

### Check
This method performs a single check for fraudulent activities. It should be used when the application need to verify a specific user action, ex. the user clicks a button in your app.
The `check` method uses a `FraudulentCheckObserver` callback which can be used for handling the Actions returned from our service or possible Failures.
```kotlin
safeSdk.check(object : FraudulentCheckObserver {
    override fun onSuccess(action: Action, signature: String) {
        // Your onSuccess logic
    }

    override fun onError(error: Failure) {
        // Your onError logic
    }
}
)
```

### Subscribe
This method initializes a subscription to the periodic check process which runs in your app every ~5 seconds. You should only really use it if you wish to monitor how the user's device detected fraudulent state changes over time, and not for a user action.
The `subscribe` method uses a `FraudulentSessionObserver` callback which you can use for handling the Actions returned from our service or possible Failures.
```kotlin
safeSdk.subscribe(object : FraudulentSessionObserver {
    override fun perform(action: Action, signature: String) {
        // Your onSuccess logic
    }

    override fun onError(error: Failure) {
        // Your onError logic
    }
}
)
```

### Unsubscribe
This method cancels the subscription mentioned above.
```kotlin
safeSdk.unsubscribe()
```
### SetUserId
This method sets a `String` object as the UserId. It is recommended to pass something unique as the UserId
```kotlin
safeSdk.setUserId(id: String)
```

### Logging
This method enables the logs for our service. This should be enabled only in debug builds.
```kotlin
safeSdk.logging(enable: Boolean)
```


_Because the Callbacks used in `check` and `subscribe` methods are similar we will merge them at a later date_

## Failures
When an error occurs, the `check` and `subscribe` methods we return the following objects (which you should handle):
```kotlin
when (error) {
    is Failure.NetworkConnection -> // Connection Error
    is Failure.NoActiveApiKey -> // The Api Key is either disabled or wrong
    is Failure.NoChecksAvailableFailure -> // The server did not find available Checks
    is Failure.NoRecipeFoundFailure -> // The application does not have a valid Recipe
    is Failure.NotInitialized -> // The SDK is not initialized
    is Failure.ServerError -> // Remote Server Error
    is Failure.UnknownError -> // Unknown Error (see Support section)
}
```

## Support
We use [Github issues](https://github.com/bespot/Antifraud-SDK-Android-Release/issues) to track bugs and enhancements.

- If you find a bug please fill out an issue report. Provide as much information as possible.
- If you think of a great idea please fill out an issue as a proposal for your idea.
