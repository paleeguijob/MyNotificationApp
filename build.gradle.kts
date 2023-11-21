// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.androidTest) apply false
    alias(libs.plugins.androidx) apply false
    alias(libs.plugins.daggerHilt) apply false
    alias(libs.plugins.jetbrainKotlinJvm) apply false
    alias(libs.plugins.kspCompiler) apply false
}
true // Needed to make the Suppress annotation work for the plugins block