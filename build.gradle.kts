// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.application) version Versions.application apply false
    id(Plugins.androidLibrary) version Versions.application apply false
    kotlin(Plugins.kotlinAndroid) version Versions.kotlinAndroid apply false
    id(Plugins.hilt) version Versions.hilt apply false
}