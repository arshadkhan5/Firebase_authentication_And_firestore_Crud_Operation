package com.example.taskbyarshad

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreViewmodel:ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    // LiveData to hold the Firestore data
    private val _userData = MutableLiveData<List<User>>()
    val userData: LiveData<List<User>> get() = _userData

    fun fetchDataFromFirestore() {
        db.collection("users")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val userList = mutableListOf<User>()
                for (document in querySnapshot) {
                    val user = document.toObject(User::class.java)
                    userList.add(user)
                }
                _userData.value = userList
            }
            .addOnFailureListener { e ->
                Log.e("FirestoreViewModel", "Error getting documents: $e")
            }
    }
    fun addUserToFirestore(user: User) {
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("FirestoreViewModel", "DocumentSnapshot added with ID: ${documentReference.id}")
                fetchDataFromFirestore() // Refresh the data after adding
            }
            .addOnFailureListener { e ->
                Log.e("FirestoreViewModel", "Error adding document", e)
            }
    }

    fun deleteUserFromFirestore(user: User) {
        db.collection("users")
            .whereEqualTo("name", user.name) // Replace with your unique identifier
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    document.reference.delete()
                    fetchDataFromFirestore() // Refresh the data after deletion
                }
            }
            .addOnFailureListener { e ->
                Log.e("FirestoreViewModel", "Error deleting document", e)
            }
    }
    fun updateUserInFirestore(user: User) {
        db.collection("users")
            .whereEqualTo("name", user.name) // Replace with your unique identifier
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    document.reference.set(user)
                    fetchDataFromFirestore() // Refresh the data after update
                }
            }
            .addOnFailureListener { e ->
                Log.e("FirestoreViewModel", "Error updating document", e)
            }
    }
}
