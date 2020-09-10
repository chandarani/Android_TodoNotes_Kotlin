cdpackage com.chanda.todonotesapp.onboarding

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.chanda.todonotesapp.R
import com.chanda.todonotesapp.utils.PrefConstant
import com.chanda.todonotesapp.utils.StoreSession
import com.chanda.todonotesapp.view.LoginActivity


class OnBoardingActivity : AppCompatActivity(), OnBoardingOneFragment.OnNextClick, OnBoardingTwoFragment.OnOptionClick {

    companion object {
        private const val FIRST_ITEM = 0
        private const val LAST_ITEM = 1
    }

    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        bindView()
        setupSharedPreference()
    }

    private fun setupSharedPreference() {
        StoreSession.init(this)
    }

    private fun bindView() {
        viewPager = findViewById(R.id.viewPager)
        val pageAdapter = FragmentAdapter(this)
        viewPager.adapter = pageAdapter
    }

    override fun onClick() {
        viewPager.currentItem = LAST_ITEM
    }

    override fun onOptionBack() {
        viewPager.currentItem = FIRST_ITEM
    }

    override fun onOptionDone() {
        StoreSession.write(PrefConstant.ON_BOARDED_SUCCESSFULLY, true)
        navigateToLogin()
    }

    private fun navigateToLogin() {
        val intent = Intent(this@OnBoardingActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}
