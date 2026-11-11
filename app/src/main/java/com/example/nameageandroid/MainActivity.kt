
package com.example.nameageandroid

import android.app.Activity
import android.os.Bundle
import android.graphics.Typeface
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {

    external fun processData(name: String, age: Int): String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER
        layout.setPadding(30, 40, 30, 40)

        val title = TextView(this)
        title.text = "مرحباً بك في تطبيقي"
        title.textSize = 25f
        title.typeface = Typeface.DEFAULT_BOLD
        title.gravity = Gravity.CENTER

        val nameInput = EditText(this)
        nameInput.hint = "اكتب اسمك هنا"
        nameInput.textSize = 18f
        nameInput.gravity = Gravity.RIGHT

        val ageInput = EditText(this)
        ageInput.hint = "اكتب عمرك هنا"
        ageInput.inputType = 2
        ageInput.textSize = 18f
        ageInput.gravity = Gravity.RIGHT

        val button = Button(this)
        button.text = "عرض الترحيب"

        val result = TextView(this)
        result.textSize = 21f
        result.gravity = Gravity.CENTER
        result.setPadding(0, 30, 0, 0)

        layout.addView(title)
        layout.addView(nameInput)
        layout.addView(ageInput)
        layout.addView(button)
        layout.addView(result)

        button.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val age = ageInput.text.toString().toIntOrNull()

            if (name.isEmpty()) {
                nameInput.error = "أدخل اسمك أولاً"
            } else if (age == null || age !in 1..120) {
                ageInput.error = "أدخل عمراً صحيحاً"
            } else {
                result.text = processData(name, age)
            }
        }

        setContentView(layout)
    }

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }
}
