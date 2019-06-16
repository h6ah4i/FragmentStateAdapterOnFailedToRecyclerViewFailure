package com.example.fragmentstateadapteronfailedtorecyclerviewfailure

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentstateadapteronfailedtorecyclerviewfailure.databinding.DemoPageFragmentBinding
import android.animation.ObjectAnimator

class DemoPageFragment: Fragment() {
    lateinit var binding: DemoPageFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DemoPageFragmentBinding.inflate(inflater, container, false)
        binding.pageNo = requireArguments().getInt("PAGE_NO")
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.text.alpha = 0f
        binding.text.animate().apply {
            alpha(1f)
            duration = 1000
            start()  // XXX: This triggers setHasTransientState(true) and causes the Design assumption violate exception
        }
    }

    companion object {
        fun create(pageNo: Int): DemoPageFragment {
            return DemoPageFragment().apply { arguments = Bundle().apply { putInt("PAGE_NO", pageNo) } }
        }
    }
}