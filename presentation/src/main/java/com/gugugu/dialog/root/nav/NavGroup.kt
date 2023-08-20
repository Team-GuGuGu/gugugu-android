package com.gugugu.dialog.root.nav

sealed class NavGroup(group: String) {

    object Test: NavGroup("test") {
        const val TEST1 = "test"
    }
}