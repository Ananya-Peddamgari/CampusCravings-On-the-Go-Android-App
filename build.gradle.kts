// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.android.application) apply false
    id("com.google.gms.google-services") version "4.4.2" apply false  // Outdated version
}

// Updated version to 4.3.15 or later for Firebase
buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.2")  // Use the latest version

    }
}
