package com.kishan.basicpdfcreater

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kishan.basicpdfcreater.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var name:String
    private lateinit var age:String
    private lateinit var rollNo:String
    private lateinit var branch:String

    companion object {
        const val NAME = "com.kishan.basicpdfcreater.MainActivity.name"
        const val AGE = "com.kishan.basicpdfcreater.MainActivity.age"
        const val ROLL_NO = "com.kishan.basicpdfcreater.MainActivity.rollNo"
        const val BRANCH = "com.kishan.basicpdfcreater.MainActivity.branch"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generatePdfBtn.setOnClickListener {
            name = binding.edName.text!!.toString()
            age = binding.edAge.text!!.toString()
            rollNo = binding.etRollNo.text!!.toString()
            branch = binding.etBranch.text!!.toString()

            val openTemplate = Intent(this,PdfTemplatePreview::class.java)

                openTemplate.putExtra(NAME, name)
                openTemplate.putExtra(AGE, age)
                openTemplate.putExtra(ROLL_NO, rollNo)
                openTemplate.putExtra(BRANCH, branch)

            startActivity(openTemplate)
//            Toast.makeText(this,"$name, $age, $rollNo, $branch" , Toast.LENGTH_LONG).show()
        }
    }
}