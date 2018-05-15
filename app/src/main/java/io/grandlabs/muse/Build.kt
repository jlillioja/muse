package io.grandlabs.muse

import io.grandlabs.muse.BuildConfig

object Build {
    const val isSml = BuildConfig.FLAVOR == "sml"
    const val isContinuum = !isSml
}