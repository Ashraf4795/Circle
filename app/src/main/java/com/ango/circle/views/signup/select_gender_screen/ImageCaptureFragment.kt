package com.ango.circle.views.signup.select_gender_screen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.ango.circle.R
import com.ango.circle.core.utils.toBitmap
import com.ango.circle.databinding.ActivityMainBinding
import com.ango.circle.databinding.FragmentImageCaptureBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ImageCaptureFragment : Fragment() {

    private val TAG = "image_capture"
    lateinit var imageCaptureBinding: FragmentImageCaptureBinding
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        imageCaptureBinding = FragmentImageCaptureBinding.inflate(layoutInflater)
        return imageCaptureBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cameraExecutor = Executors.newSingleThreadExecutor()
        initTakePictureButtonClickListener()

    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    private fun initTakePictureButtonClickListener() {
        takePicture()
    }

    private fun takePicture(){
        val imageCapture = view?.display?.let {
            ImageCapture.Builder()
                .setTargetRotation(it.rotation)
                .build()
        }

        val cameraProviderFuture = ProcessCameraProvider.getInstance(this.requireActivity())
        cameraProviderFuture.addListener(Runnable {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(imageCaptureBinding.viewFinder.surfaceProvider)
                }

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    viewLifecycleOwner, cameraSelector, preview,imageCapture)

            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this.requireActivity()))

        imageCapture?.takePicture(cameraExecutor,object: ImageCapture.OnImageCapturedCallback() {
            @SuppressLint("UnsafeOptInUsageError")
            override fun onCaptureSuccess(imageProxy: ImageProxy) {

                println(imageProxy)
                val rotationDegree = imageProxy.imageInfo.rotationDegrees
                val bitmap: Bitmap? = imageProxy.image?.toBitmap(rotationDegree)

                super.onCaptureSuccess(imageProxy)
            }
        })
    }

}