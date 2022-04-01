package com.example.androidlessonspart2.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlessonspart2.R
import com.example.androidlessonspart2.databinding.ActivityFragmentHostBinding
import com.example.androidlessonspart2.ui.fragments.FragmentFirst

class FragmentHostActivity: AppCompatActivity() {

    private lateinit var binding: ActivityFragmentHostBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }


    private fun initLayout() {
        binding.apply {
        // load first fragment
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, FragmentFirst.newInstance())
                .commitAllowingStateLoss()
        }
    }

    /*
    Explain difference between replace and add.
    replace removes the existing fragment and adds a new fragment. This means when you press back button the fragment
    that got replaced will be created with its onCreateView being invoked. Whereas add retains the existing fragments
    and adds a new fragment that means existing fragment will be active and they wont be in 'paused' state hence when
    a back button is pressed onCreateView is not called for the existing fragment(the fragment which was there before
    new fragment was added).
     In terms of fragment's life cycle events onPause, onResume, onCreateView and other life cycle events will be invoked
     in case of replace but they wont be invoked in case of add.
     */


    /*
    Difference between commit and commitAllowingStateLoss

    commit: Schedules a commit of this transaction. The commit does not happen immediately;
    it will be scheduled as work on the main thread to be done the next time that thread is ready.

    commitAllowingStateLoss: A transaction can only be committed with this method prior to its containing
    activity saving its state. If the commit is attempted after that point, an exception will be thrown.
    This is because the state after the commit can be lost if the activity needs to be restored from its state.



     */
}