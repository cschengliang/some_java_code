use atguigu1;
create table t_score(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    student_id VARCHAR(50) NOT NULL,
    score INT NOT NULL
);

desc t_score;

select * from t_score;

insert into t_score(name, student_id, score) VALUES (?,?,?);