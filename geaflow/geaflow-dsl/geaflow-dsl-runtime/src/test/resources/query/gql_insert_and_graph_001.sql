CREATE TABLE tbl_result (
  a_id bigint,
  weight double,
  b_id bigint
) WITH (
	type='file',
	geaflow.dsl.file.path='${target}'
);

USE GRAPH dy_modern;

INSERT INTO dy_modern.person(id, name, age)
SELECT 1, 'jim', 20
UNION ALL
SELECT 2, 'kate', 22
;

INSERT INTO dy_modern.knows
SELECT 1, 2, 0.2
;


INSERT INTO dy_modern(person.id, person.name, knows.srcId, knows.targetId)
SELECT 3, 'jim', 3, 2
;

INSERT INTO tbl_result
SELECT
	a_id,
	weight,
	b_id
FROM (
  MATCH (a:person where id = 1) -[e:knows]->(b:person)
  RETURN a.id as a_id, e.weight as weight, b.id as b_id
)
