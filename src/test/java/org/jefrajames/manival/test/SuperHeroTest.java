package org.jefrajames.manival.test;

import org.jefrajames.manival.schema.queries.*;
import org.junit.Test;

import java.util.List;

import static java.lang.System.out;
import static org.junit.Assert.*;

public class SuperHeroTest {

    private static String SERVER_URL = "http://localhost:8080/graphql";

    @Test
    public void testAllHeroes() {
        AllHeroesQuery query = AllHeroesQuery.builder().build();
        AllHeroesQuery.Result result = query.request(SERVER_URL).post();

        assertNotNull(result.getAllHeroes());
        int size = result.getAllHeroes().size();
        out.println("heroes size=" + size);
        assertTrue(size >= 4);

        List<AllHeroesQuery.Result.allHeroes> heroes = result.getAllHeroes();
        for (AllHeroesQuery.Result.allHeroes hero : heroes) {
            out.println("\thero.name=" + hero.getName());
            out.println("\thero realName=" + hero.getRealName());
            out.println("\thero primaryLocation=" + hero.getPrimaryLocation());
        }

    }

    @Test
    public void testHeroesInTeam() {
        AllHeroesInTeamQuery query = AllHeroesInTeamQuery.builder().withTeamName("Avengers").build();
        AllHeroesInTeamQuery.Result result = query.request(SERVER_URL).post();

        assertNotNull(result.getAllHeroesInTeam());
        int size = result.getAllHeroesInTeam().size();
        out.println("allHeroesInTeam size=" + size);
        assertTrue(size >= 3);

        List<AllHeroesInTeamQuery.Result.allHeroesInTeam> heroesInTeam = result.getAllHeroesInTeam();
        for (AllHeroesInTeamQuery.Result.allHeroesInTeam heroInTeam : heroesInTeam) {
            out.println("\thero.name=" + heroInTeam.getName());
            out.println("\thero realName=" + heroInTeam.getRealName());
            for (String superPower : heroInTeam.getSuperPowers()) {
                out.println("\t\thero.superpower=" + superPower);
            }
        }

    }

    @Test
    public void testHeroByName() {

        FindHeroByNameQuery query = FindHeroByNameQuery.builder().withHeroName("Spider Man").build();
        FindHeroByNameQuery.Result result = query.request(SERVER_URL).post();
        FindHeroByNameQuery.Result.findHeroByName hero = result.getFindHeroByName();

        assertNotNull(hero.getRealName());
        assertTrue(hero.getRealName().equals("Peter Parker"));

        out.println("hero.name=" + hero.getName());
        out.println("hero.realName=" + hero.getRealName());

        List<FindHeroByNameQuery.Result.findHeroByName.teamAffiliations> teamAffiliations = hero.getTeamAffiliations();

        for (FindHeroByNameQuery.Result.findHeroByName.teamAffiliations affiliation : teamAffiliations) {
            out.println("hero.teamAffiliation=" + affiliation.getName());
        }

    }

    @Test
    public void testAllTeams() {
        AllTeamsQuery query = AllTeamsQuery.builder().build();
        AllTeamsQuery.Result result = query.request(SERVER_URL).post();

        int size = result.getAllTeams().size();
        out.println("team size=" + size);
        assertTrue(size >= 3);

        List<AllTeamsQuery.Result.allTeams> teams = result.getAllTeams();
        for (AllTeamsQuery.Result.allTeams team : teams)
            out.println("team name=" + team.getName());
    }

    @Test
    public void testAddHeroToTeam() {
        AddHeroToTeam mutation = AddHeroToTeam.builder().withHeroName("Spider Man").withTeamName("X-Men").build();
        AddHeroToTeam.Result result = mutation.request(SERVER_URL).post();

        assertNotNull(result.getAddHeroToTeam());
        assertTrue(result.getAddHeroToTeam().getMembers().size() >= 1);

        out.println("member count=" + result.getAddHeroToTeam().getMembers().size());
        for (AddHeroToTeam.Result.addHeroToTeam.members member : result.getAddHeroToTeam().getMembers()) {
            out.println("\t member.name=" + member.getName());
            out.println("\tmember.realName" + member.getRealName());
            out.println("\tmember.primaryLocation=" + member.getPrimaryLocation());
        }

    }

    @Test
    public void testCreateHero() {
        CreateNewHero mutation = CreateNewHero.builder()
                .withName("Bruce Lee")
                .withRealName("Lee Jun Fan")
                .withPrimaryLocation("San Francisco")
                .withSuperPowers(List.of("Jet Kune Do", "Fitness"))
                .withTeamName("Martial artist")
                .build();

        CreateNewHero.Result result = mutation.request(SERVER_URL).post();

        assertNotNull(result.getCreateNewHero());
        assertEquals(result.getCreateNewHero().getRealName(), "Lee Jun Fan");
        out.println("result.createNewHero=" + result.getCreateNewHero().getRealName());
    }

}