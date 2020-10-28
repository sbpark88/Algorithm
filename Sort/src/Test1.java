public class Test1 {
    public static class Student {
        String name;
        int kor;
        int eng;
        int math;
        int total;

        public Student(String name, int kor, int eng, int math) {
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
            this.calcTotalScore(kor, eng, math);
        }

        public void calcTotalScore(int kor, int eng, int math) {
            this.total = kor + eng + math;
        }
    }

    public static void main(String[] args) {
        String name[] = {"A","B","C","D","E"};
        int kor[] = {50,60,70,80,90};
        int eng[] = {100,70,55,20,100};
        int math[] = {95,80,90,85,80};

        Student[] student = new Student[5];

        for (int i = 0; i < student.length; i++) {
            student[i] = new Student(name[i], kor[i], eng[i], math[i]);
        }

        // 오름차순
//        for (int i = 0; i < student.length; i++) {
//            for (int j = i; j < student.length; j++) {
//                if (student[i].total > student[j].total) {    // 앞이 더 크다면 바꿔라.
//                    Student temp = student[i];
//                    student[i] = student[j];
//                    student[j] = temp;
//                } else {
//                    continue;
//                }
//            }
//        }

        // 내림차숨
        for (int i = 0; i < student.length; i++) {
            for (int j = i; j < student.length; j++) {
                if (student[i].total < student[j].total) {  // 뒤가 더 크다면 바꿔라.
                    Student temp = student[i];
                    student[i] = student[j];
                    student[j] = temp;
                } else {
                    continue;
                }
            }
        }

        for (int i = 0; i < student.length; i++) {
            System.out.printf("%d등 : %s, %d  \n", i+1, student[i].name, student[i].total);
        }
    }
}
