# Android library for storing passwords securely (Java)

## Overview
The purpose or this project is to provide an easy-to-use, secure library for storing user credentials and sensitive information.
The default Android SharedPreferences are saved in a plain text XML file in the app's internal storage, so not a good place to store sensitive information.

This project creates a wrapper around the standard SharedPreferences class that encrypts both preference keys and values with a strong encryption algorithm.

Both preference keys and values are encrypted using AES-256 CBC mode. Encryption keys are generated when the SecurePreferences are intialized and stored in the Android KeyStore.

Warning: Use at your own risk. The library has not been tested in a production app

## Supporting material
For more detailed information about the implementation, please see my [blog post on storing passwords securely](http://justmobiledev.com/storing-passwords-securely-on-android/).

The Android documentation on [the Android Keystore System](https://developer.android.com/training/articles/keystore) is a good read to better understand the project.

## Getting Started
Add the SecurePreferences library to your root build.gradle at the end of repositories:

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add the dependency to your app bild.gradle:

```
dependencies {
	        implementation 'com.github.justmobiledev:android-secure-preferences-lib-1:1.0'
	}
```

## Usage
```java
        final String MY_PASSWORD_KEY = "my_password_key";

        // Create Secure Preferences Container
        SecurePasswordContainer secureContainer = SecurePasswordContainer.create(MyApplication.getInstance());

        // Store a password
        SecurePasswordContainer.set(MY_PASSWORD_KEY, password);

        // Retrieve password
        String retrievedPassword = SecurePasswordContainer.get(MY_PASSWORD_KEY, "");

```

## Implementation
* The SecurePrefsBuilder is a builder class used to specify properties for your shared preferences, e.g. file name, or if keys should be encrypted.
* The wrapper around the standard SharedPreferences is implemented in the SecurePrefs class.
* The project uses the Android KeyGenerator to create an AES-256 encryption key, which is safely stored in the Keystore.
* When you set values, the key and the value is encrypted in the SharedPrefs class with the encryption key retrieved from the Keystore before they are stored in the shared preferences XML file.
* When you get values, they key is encrypted to look up the value, the value is decrypted from the shared preferences XML file. For both actions the encryption key is retrieved from the Keystore.
* To see how your key and value are stored, open the 'Device Explorer' from the right Android Studio tab, and go to data\data\com.mobile.justmobiledev.androidsecurepreferences1\shared_prefs\my_secure_prefs_file.xml.
You can right-click on the file and 'Save As' to a temp directory on your computer. The file content should show the encrypted key and value, e.g.

```xml
<?xml version='1.0' encoding='utf-8' standalone='yes' ?>
<map>
    <string name="SdfDPM+/YYxAxqT6uglWlw==">3GieiQ1hCS1V0X5D0srbng==</string>
    <string name="kUYzK2T9kDFhL/RYSSVY8g==">OwhEqVBCrZwnZwUDfI9ijQ==</string>
</map>
```

## Sample App Usage
1. When the app is started, you can enter a preference value into the first field.
2. Press the 'Start' button to encrypt key and value and store them into the preferences xml file
3. The app fetches the preferences again, decrypts the value and displays them in the second text field.

## Contributors
Contributors are welcome to extend features, e.g. select encryption algorithm, ability to store other data types, password hash option, etc.

## Screenshots
![Secure Passwords](screenshots/secure-passwords-ss-1.png?raw=true "Secure Passwords")
