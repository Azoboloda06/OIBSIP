package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QUIZ extends AppCompatActivity {
    TextView numbering, question;
    RadioGroup options;
    RadioButton option1, option2, option3, option4;
    Button nextButton, finishButton;
    ArrayList<Question> questions = new ArrayList<>();
    int currentQuestion = 0;
    int correctScore;
    int incorrectScore;
    boolean answered = false;
    RadioGroup.OnCheckedChangeListener answerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        numbering = findViewById(R.id.questionNumber);
        question = findViewById(R.id.questionTextView);
        options = findViewById(R.id.groupRadio);
        option1 =findViewById(R.id.option1);
        option2 =findViewById(R.id.option2);
        option3 =findViewById(R.id.option3);
        option4 =findViewById(R.id.option4);
        nextButton = findViewById(R.id.nextBtn);
        finishButton = findViewById(R.id.finishBtn);

        createQ();
        Log.d("QUIZ", "questions.size() = " + questions.size());
        Collections.shuffle(questions);

        answerListener = (group, checkedId) -> {
            if (checkedId == -1) return;
            if (answered) return;
            answered = true;

            RadioButton selected = findViewById(checkedId);
            int selectedAnswer = group.indexOfChild(selected);
            Question current = questions.get(currentQuestion);

            if (selectedAnswer == current.getCorrectAnswer()) {
                correctScore++;
                selected.setBackgroundColor(getColor(R.color.correct_green));
            } else {
                incorrectScore++;
                selected.setBackgroundColor(getColor(R.color.wrong_red));
            }

            for (int i = 0; i < group.getChildCount(); i++) {
                group.getChildAt(i).setEnabled(false);
            }
        };

        showQuestion();

        nextButton.setOnClickListener(v -> {
            int selectedId = options.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
                return;
            }

            if (currentQuestion < questions.size() - 1) {
                currentQuestion++;
                showQuestion();
            }
        });

        finishButton.setOnClickListener(v -> {
            int selectedId = options.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(QUIZ.this, Results.class);

            intent.putExtra("correctScore", correctScore);
            intent.putExtra("incorrectScore", incorrectScore);

            startActivity(intent);
            finish();
        });

    }

    public void createQ(){
        questions.add(new Question(
                "Which planet is known as the Red Planet?",
                new String[]{"Earth", "Mars", "Jupiter", "Venus"},
                1
        ));

        questions.add(new Question(
                "What is the largest planet in our Solar System?",
                new String[]{"Earth", "Saturn", "Jupiter", "Neptune"},
                2
        ));

        questions.add(new Question(
                "What is the name of the galaxy we live in?",
                new String[]{"Andromeda", "Milky Way", "Whirlpool", "Sombrero"},
                1
        ));

        questions.add(new Question(
                "Which star is at the center of our Solar System?",
                new String[]{"Sirius", "Polaris", "The Sun", "Betelgeuse"},
                2
        ));

        questions.add(new Question(
                "How many planets are in our Solar System?",
                new String[]{"7", "8", "9", "10"},
                1
        ));

        questions.add(new Question(
                "Which planet is famous for its prominent ring system?",
                new String[]{"Mars", "Venus", "Saturn", "Mercury"},
                2
        ));

        questions.add(new Question(
                "What force keeps planets in orbit around the Sun?",
                new String[]{"Magnetism", "Friction", "Gravity", "Electricity"},
                2
        ));

        questions.add(new Question(
                "What is the name of the first human-made satellite launched into space?",
                new String[]{"Apollo 11", "Sputnik 1", "Voyager 1", "Hubble"},
                1
        ));

        questions.add(new Question(
                "What is a light-year a measurement of?",
                new String[]{"Time", "Brightness", "Distance", "Temperature"},
                2
        ));

        questions.add(new Question(
                "What is the event horizon of a black hole?",
                new String[]{
                        "The center of the black hole",
                        "The boundary beyond which light cannot escape",
                        "A region where stars are born",
                        "The outer surface of the black hole"
                },
                1
        ));
    }

    private void showQuestion() {

        options.setOnCheckedChangeListener(null);
        answered = false;

        Question current = questions.get(currentQuestion);
        numbering.setText("Question " + (currentQuestion + 1) + " of " + questions.size());
        question.setText(current.getQuestionText());

        String[] answers = current.getOptions();
        option1.setText(answers[0]);
        option2.setText(answers[1]);
        option3.setText(answers[2]);
        option4.setText(answers[3]);

        options.clearCheck();

        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);

        option1.setBackground(null);
        option2.setBackground(null);
        option3.setBackground(null);
        option4.setBackground(null);

        if (currentQuestion == questions.size() - 1) {
            nextButton.setVisibility(View.GONE);
            finishButton.setVisibility(View.VISIBLE);
        } else {
            nextButton.setVisibility(View.VISIBLE);
            finishButton.setVisibility(View.GONE);
        }

        options.setOnCheckedChangeListener(answerListener);
    }

}