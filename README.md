# Happy-Programming

#procedue: count popular skill
CREATE PROCEDURE popular_skill
AS
select b.skill_name,b.[img],count(a.skill_id) as countSkill from mentor_skills as a inner join skill
as b on a.skill_id=b.id group by b.skill_name,b.[img] order by countSkill desc
