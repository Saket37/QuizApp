package com.example.quizapp.data.quiz.impl

import com.example.quizapp.R
import com.example.quizapp.models.Questions

val question1 = Questions(
    id = "1",
    question = "Who is the person in image?",
    questionImage = R.drawable.quiz_q1,
    option1 = "Panini",
    option2 = "Kalidasa",
    option3 = "Chanakya",
    option4 = "Aryabhata",
    answer = "Aryabhata"
)
val question2 = Questions(
    id = "2",
    question = "What country does this flag belong to",
    questionImage = R.drawable.quiz_q2,
    option1 = "New Zealand",
    option2 = "Australia",
    option3 = "USA",
    option4 = "Liberia",
    answer = "Australia"
)
val question3 = Questions(
    id = "3",
    question = "Which company is known for publishing Mario video game",
    questionImage = R.drawable.quiz_q3,
    option1 = "SEGA",
    option2 = "Electronic Arts",
    option3 = "Nintendo",
    option4 = "Activision",
    answer = "Nintendo"
)
val question4 = Questions(
    id = "4",
    question = "In which year GTA: San Andreas was released",
    questionImage = R.drawable.quiz_q4,
    option1 = "2004",
    option2 = "2005",
    option3 = "2003",
    option4 = "2001",
    answer = "2004"
)
val question5 = Questions(
    id = "5", question = "What country does this flag belong to",
    questionImage = R.drawable.quiz_q5,
    option1 = "Nepal",
    option2 = "Bhutan",
    option3 = "Myanmar",
    option4 = "Sri Lanka",
    answer = "Bhutan"
)

val quizQuestions: List<Questions> = listOf(question1, question2, question3, question4, question5)