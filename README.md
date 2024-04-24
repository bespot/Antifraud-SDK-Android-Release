# Antifraud SDK Android Release

[![VERSION](https://img.shields.io/badge/VERSION-0.4.8-green)](#)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat)](#)

Antifraud SDK is an easy to use Android library for keeping your app safe from fraudulent activities.
The SDK needs minimal permissions to work and uses device information and sensors in order to detect and report potential threats,
so the app developers can determine the next best move for their apps!

## Features
|   Detection     	   | Supported  	 |          Description                    	           |
|:-------------------:|:------------:|:---------------------------------------------------:|
|    VPN        	     |   yes    	   |       Virtual Private Network              	        |
|  Mock Location   	  |   yes    	   | Another app is manipulating the device's location 	 |
| Multiple  Users  	  |   yes    	   |  More than one users are registered in the device	  |

## Installation

<img src="screenshots/sample_start.png" width="300" align="right" hspace="20">

Add the following lines inside the `dependencyResolutionManagement` bracket of `settings.gradle.kts` 
(or inside the `repositories` bracket inside the root `build.gradle.kts` file if you still use the old way)

```kotlin
dependencyResolutionManagement {
    maven(url = "https://artifactory.bespot.com/artifactory/bespot-antifraud")
    maven(url = "https://artifactory.bespot.com/artifactory/bespot-logger")
    maven(url = "https://jitpack.io" )
}
```
_The `bespot-logger` dependency is needed in order to have available logs for our mobile developers to make debugging easier,
and the `jitpack` dependency is needed for  some dependencies our SDK has. We will try to remove them at a later date_

Next add the following dependency inside your app's `build.gradle.kts` file
```kotlin
dependencies {
    implementation("com.bespot.antifraud:sdk-android:$latest_version")
}
```

Finally it is required to pass the `API_KEY` and `API_URL` as `resValues` inside your app's `build.gradle.kts` file. Like the following example
```kotlin
resValue("string", "antifraud_sdk_key", YOUR_API_KEY)
resValue("string", "antifraud_sdk_api_url", YOUR_API_URL)
```
Or via your app's `strings.xml` file
```xml
<string name="antifraud_sdk_key">YOUR_API_KEY</string>
<string name="antifraud_sdk_api_url", YOUR_API_URL</string>
```
## Usage

<img src="screenshots/sample_subscribe.png" width="300" align="right" hspace="20">

Our SDK has the following available methods

### Check
This method checks a single time for fraudulent activities. It is recommended to be used when the user does a simple thing, ex. clicks a button in your app.
The `check` method uses a `FraudulentCheckObserver` callback which you can use for handling the Actions returned from our service or possible Failures
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
This method initializes a subscription which will return a check to your app every ~5 seconds. It is recommended to use it for a 
specific flow in your app, and not when a user does a simple thing like selecting a button.
The `subscribe` method uses a `FraudulentSessionObserver` callback which you can use for handling the Actions returned from our service or possible Failures
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
This method set a `String` object as the UserId. It is recommended to pass something unique as the UserId
```kotlin
safeSdk.setUserId(id: String)
```

### Logging
This method enables the logs for our service. It is recommended to be enabled only in debug builds.
```kotlin
safeSdk.logging(enable: Boolean)
```


_Because the Callbacks used in `check` and `subscribe` methods are similar we will merge them at a later date_

## Failures
In the `check` and `subscribe` methods we mentioned some Failures. At this time these are the available Failures which we recommend you handle them.
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
