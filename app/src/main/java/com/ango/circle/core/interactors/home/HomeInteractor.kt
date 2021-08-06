package com.ango.circle.core.interactors.home

import com.ango.circle.core.interactors.FirebaseInteractor

class HomeInteractor(private val firebaseInteractor: FirebaseInteractor) : IHomeInteractor by firebaseInteractor