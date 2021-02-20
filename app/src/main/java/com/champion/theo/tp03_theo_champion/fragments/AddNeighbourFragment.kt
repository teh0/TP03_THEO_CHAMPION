package com.champion.theo.tp03_theo_champion.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import com.basgeekball.awesomevalidation.AwesomeValidation as Validation
import com.champion.theo.tp03_theo_champion.R
import com.champion.theo.tp03_theo_champion.contracts.NavigationListener
import com.champion.theo.tp03_theo_champion.databinding.AddNeighborBinding
import com.champion.theo.tp03_theo_champion.models.Neighbor
import com.champion.theo.tp03_theo_champion.repositories.NeighborRepository
import java.util.*

class AddNeighbourFragment: Fragment() {
    private var _binding: AddNeighborBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationListener) {
            context.updateTitle(R.string.title_neighbor_add)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddNeighborBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onClickSubmitButton()
    }

    private fun handleFromValidation() {
        val formValidation = Validation(ValidationStyle.BASIC)
        // Image
        formValidation.addValidation(activity, binding.imageLayoutText.id, RegexTemplate.NOT_EMPTY, R.string.validation_required)
        formValidation.addValidation(activity, binding.imageLayoutText.id, RegexTemplate.NOT_EMPTY, R.string.validation_web_url)
        // Name
        formValidation.addValidation(activity, binding.nameLayoutText.id, RegexTemplate.NOT_EMPTY, R.string.validation_required)
        // Phone
        formValidation.addValidation(activity, binding.phoneLayout.id, RegexTemplate.NOT_EMPTY, R.string.validation_required)
        formValidation.addValidation(activity, binding.phoneLayoutText.id, android.util.Patterns.PHONE, R.string.validation_phone)
        // Website
        formValidation.addValidation(activity, binding.websiteLayoutText.id, android.util.Patterns.WEB_URL, R.string.validation_required)
        formValidation.addValidation(activity, binding.websiteLayoutText.id, RegexTemplate.NOT_EMPTY, R.string.validation_web_url)
        // Address
        formValidation.addValidation(activity, binding.addressLayoutText.id, RegexTemplate.NOT_EMPTY, R.string.validation_web_url)
        // About
        formValidation.addValidation(activity, binding.aboutLayoutText.id, RegexTemplate.NOT_EMPTY, R.string.validation_web_url)
        //formValidation.addValidation(activity, binding.aboutLayoutText.id, "/^[a-z]{0,30}\$/", R.string.validation_max_charac_30)



        formValidation.validate()
    }

    private fun onClickSubmitButton() {
        binding.submitButton.setOnClickListener(View.OnClickListener {
            handleFromValidation()

            val newNeigbor = Neighbor(
                UUID.randomUUID().toString(),
                binding.nameLayoutText.text.toString(),
                binding.imageLayoutText.text.toString(),
                binding.addressLayoutText.text.toString(),
                binding.phoneLayoutText.text.toString(),
                binding.aboutLayoutText.text.toString(),
                false,
                binding.websiteLayoutText.text.toString()
            )
            NeighborRepository.getInstance().create(newNeigbor)
            print("After created neighbor")
            (activity as? NavigationListener)?.let {
                it.showFragment(ListNeigborsFragment())
            }

        })
    }

}