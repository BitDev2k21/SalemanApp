package com.saleman.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.common.api.ApiException
import com.saleman.R
import com.saleman.api.ApiCallingRequest
import com.saleman.utils.MyProgressDialog
import com.saleman.utils.PopupUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception
import java.util.HashMap

class RegisterActivity : BaseActivity() {

    private lateinit var rlLoginSignUp: RelativeLayout
    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var edtConfirmPassword: EditText
    private lateinit var edtContactNo: EditText
    private lateinit var pd: MyProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        try {
            init()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun init() {
        rlLoginSignUp = findViewById(R.id.rlLoginSignUp)
        edtName = findViewById(R.id.edtName)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword)
        edtContactNo = findViewById(R.id.edtContactNo)
        pd = MyProgressDialog(this, R.drawable.icons8_loader)
        rlLoginSignUp.setOnClickListener {
            if (isValidateData()) {
                apiCallingForRegister(
                        edtName.text.toString().trim(),
                        edtEmail.text.toString().trim(),
                        edtPassword.text.toString().trim(),
                        edtContactNo.text.toString().trim()
                )
            }
        }

    }

    private fun isValidateData(): Boolean {
        val name = edtName.text.toString().trim()
        val user = edtEmail.text.toString().trim()
        val password = edtPassword.text.toString().trim()
        val phone = edtContactNo.text.toString().trim()
        val confirmPassword = edtConfirmPassword.text.toString().trim()
        if (TextUtils.isEmpty(name)) {
            PopupUtils.alertMessage(this, "Please enter name")
            return false
        } else if (TextUtils.isEmpty(user)) {
            PopupUtils.alertMessage(this, "Please enter valid email")
            return false
        } else if (TextUtils.isEmpty(phone)) {
            PopupUtils.alertMessage(this, "Please enter phone no")
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
            PopupUtils.alertMessage(this, "Please enter valid email")
            return false
        } else if (TextUtils.isEmpty(password)) {
            PopupUtils.alertMessage(this, "Please enter password")
            return false
        } else if (TextUtils.isEmpty(confirmPassword)) {
            PopupUtils.alertMessage(this, "Please enter confirm password")
            return false
        } else if (!password.equals(confirmPassword)) {
            PopupUtils.alertMessage(this, "Password and confirm password not macth")
            return false
        } else {
            return true
        }
    }

    private fun apiCallingForRegister(name: String, email: String, password: String, phone: String) {
        pd.show()
        lifecycleScope.launch(Dispatchers.IO) {
            val apiCallingRequest = com.saleman.api.ApiCallingRequest()
            val params = HashMap<String, String>()
            params["name"] = name
            params["email"] = email
            params["password"] = password
            params["user_type"] = "retailer"
            params["contact"] = phone
            //contact
            try {
                val responseOfRegister = apiCallingRequest.apiCallingRegister(params)
                withContext(Dispatchers.Main) {
                    pd.cancel()
                    PopupUtils.alertMessageWithCallBack(
                            this@RegisterActivity,
                            "successful user registration",
                            {
                                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                                finishAffinity()
                            })
                }
            } catch (apiEx: ApiException) {
                withContext(Dispatchers.Main) {
                    pd.cancel()
                    PopupUtils.alertMessage(this@RegisterActivity, "Something went wrong, Please try again.")
                }
            }
        }
    }


}