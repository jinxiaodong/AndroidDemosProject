package com.jarvis.kotlindemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.jarvis.kotlindemo.entity.User
import com.jarvis.kotlindemo.widget.CodeView

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private val userNameKey = "userName"
    private val passwordKey = "password"


    private lateinit var etUserName: EditText
    private lateinit var etPassWord: EditText
    private lateinit var etCode: EditText
    private lateinit var codeView: CodeView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etUserName = findViewById(R.id.et_username)
        etPassWord = findViewById(R.id.et_password)
        etCode = findViewById(R.id.et_code)
        codeView = findViewById(R.id.code_view)

        val btnLogin = findViewById<Button>(R.id.btn_login)

        etUserName.setText("")

        btnLogin.setOnClickListener(this)
        codeView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v is Button) {
            //智能转换
            // val button = v as Button
            login()
        } else if (v is CodeView) {
            v.updateCode()
        }

    }

    private fun login() {
        val userName = etUserName.text.toString().trim()
        val passWord = etPassWord.text.toString().trim()
        val code = etCode.text.toString().trim()
//        etCode.text = Editable.Factory.getInstance().newEditable("1")

        val user = User(userName, passWord, code)

        //函数嵌套
        fun verify(): Boolean {
            if (user.username?.length ?: 0 < 4) {
                Toast.makeText(this, "用户名称不合法", Toast.LENGTH_SHORT).show()
                return false
            }
            if (user.password?.length ?: 0 < 4) {
                Toast.makeText(this, "密码不合法", Toast.LENGTH_SHORT).show()
                return false
            }
            return true
        }

        if (verify()) {
            startActivity(Intent(this, LessonActivity::class.java))

        }

    }


}