package com.ango.circle.core.interactors.category

import com.ango.circle.core.interactors.FirebaseInteractor
import com.ango.circle.core.state.State
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class CategoryInteractorImpl(val firebaseInteractor: FirebaseInteractor): ICategoryInteractor by firebaseInteractor