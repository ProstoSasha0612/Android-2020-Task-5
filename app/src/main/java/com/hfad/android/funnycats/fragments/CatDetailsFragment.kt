package com.hfad.android.funnycats.fragments

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.hfad.android.funnycats.R
import com.hfad.android.funnycats.databinding.CatDetailsFragmntFragmentBinding

class CatDetailsFragment : Fragment() {

    private var _binding: CatDetailsFragmntFragmentBinding? = null
    private val binding
        get() = requireNotNull(_binding)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CatDetailsFragmntFragmentBinding.inflate(layoutInflater)
        //TODO change it and make with old cat list saving and rotation support add
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    parentFragmentManager.beginTransaction()
                        .setCustomAnimations(R.animator.back_animator, R.animator.front_animator)
                        .replace(R.id.container, CatListFragment.newInstance())
                        .commit()
                }
            })
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.setOnClickListener {
            saveImageToGallery()
            Toast.makeText(context, "Cat saved into your gallery ;)", Toast.LENGTH_LONG).show()
        }
        //Getting acquired image from CatListFragment
        val byteArray = arguments?.getByteArray(IMAGE)
        val bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray?.size?:0)
        binding.catDetailImage.setImageBitmap(bitmap)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //Todo make other image saving into the gallery
    private fun saveImageToGallery() {
        val drawable = binding.catDetailImage.drawable
        val bitmap = (drawable as BitmapDrawable).bitmap
        MediaStore.Images.Media.insertImage(
            activity?.contentResolver,
            bitmap,
            "Cat image",
            "Description"
        )
        //Андрей, если ты это читаешь, то не бей палками, я так сделал потому что хотел красиво, а не хватило времени, поэтому
        // не успел разобраться какой сейчас метод сохранения картинки в галерею и в итоге пришлось пользоваться этим,
        //который оказался deprecated
    }

    companion object {
        fun newInstance(byteArray: ByteArray): CatDetailsFragment {
            val bundle = bundleOf(IMAGE to byteArray)
            val fragment = CatDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }

        const val IMAGE = "Image"
    }
}