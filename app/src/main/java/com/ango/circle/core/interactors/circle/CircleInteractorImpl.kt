package com.ango.circle.core.interactors.circle

import com.ango.circle.core.circlesCollection
import com.ango.circle.core.data.model.Circle
import com.ango.circle.core.state.ErrorState
import com.ango.circle.core.state.State
import com.ango.circle.core.state.SuccessState
import com.google.firebase.firestore.FirebaseFirestore

class CircleInteractorImpl(val firestore: FirebaseFirestore) : ICircleInteractor {

    override suspend fun getCircles(onCompleteListener: (State) -> Unit) {
        val circleList = mutableListOf<Circle>()
        firestore.collection(circlesCollection)
            .get()
            .addOnSuccessListener {
                it.documents.forEach { documentSnapshot ->
                    val circle = documentSnapshot.toObject(Circle::class.java)
                    circle?.let { it ->
                        circleList.add(it)
                    }
                }
                onCompleteListener(SuccessState(data = circleList))

            }
            .addOnFailureListener {
                onCompleteListener(
                    ErrorState(
                        errorCode = it.message,
                        message = "failed to fetch cirlces"
                    )
                )
            }
    }

    override suspend fun getCirclesByName(
        query: String,
        onCompleteListener: (State) -> Unit
    ) {
        getCircleByNameWithoutCategory(query, onCompleteListener)
    }


    private fun getCircleByNameWithoutCategory(
        query: String,
        onCompleteListener: (State) -> Unit
    ) {
        val circleList = mutableListOf<Circle>()
        firestore.collection(circlesCollection)
            .whereGreaterThanOrEqualTo("circleTitle", query.toUpperCase())
            .get()
            .addOnSuccessListener {
                it.documents.forEach { documentSnapshot ->
                    val circle = documentSnapshot.toObject(Circle::class.java)
                    circle?.let { it ->
                        circleList.add(it)
                    }
                }
                onCompleteListener(SuccessState(data = circleList))

            }
            .addOnFailureListener {
                onCompleteListener(
                    ErrorState(
                        errorCode = it.message,
                        message = "failed to fetch cirlces"
                    )
                )
            }
    }
}