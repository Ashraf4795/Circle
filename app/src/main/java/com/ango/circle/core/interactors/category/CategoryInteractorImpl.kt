package com.ango.circle.core.interactors.category

import com.ango.circle.core.categoryCollection
import com.ango.circle.core.data.model.Category
import com.ango.circle.core.state.ErrorState
import com.ango.circle.core.state.State
import com.ango.circle.core.state.SuccessState
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class CategoryInteractorImpl(val firestore: FirebaseFirestore): ICategoryInteractor {

    override suspend fun getCategories(onCompleteListener: (State) -> Unit) {
        val categoryList = mutableListOf<Category>()
        firestore.collection(categoryCollection)
            .get()
            .addOnSuccessListener {
                it.documents.forEach { documentSnapshot ->
                    val category = documentSnapshot.toObject(Category::class.java)
                    category?.let { it ->
                        categoryList.add(it)
                    }
                }
                onCompleteListener(SuccessState(data = categoryList))

            }
            .addOnFailureListener {
                onCompleteListener(
                    ErrorState(
                        errorCode = it.message,
                        message = "failed to fetch cirlce categories"
                    )
                )
            }
    }
}