package io.grandlabs.muse

import rx.Observable
import rx.lang.kotlin.BehaviorSubject

/**
 * Created by jacob on 3/27/18.
 */

interface NavigationProvider {
    val actions: Observable<NavigationAction>
}

interface NavigationController {
    fun navigateTo(action: NavigationAction)
}

class Navigator: NavigationProvider, NavigationController {
    private val actionsSubject = BehaviorSubject<NavigationAction>()
    override val actions: Observable<NavigationAction> = actionsSubject.asObservable()

    override fun navigateTo(action: NavigationAction) {
        actionsSubject.onNext(action)
    }
}

enum class NavigationAction {
    ENTRY_LIST,
    CREATE_ENTRY,
    TYPE_AND_FEELING_TAG_SELECTOR,
    WHO_AND_WHERE_TAG_SELECTOR,
    PRIORITY_SELECTOR,
    SEND_ENTRY
}