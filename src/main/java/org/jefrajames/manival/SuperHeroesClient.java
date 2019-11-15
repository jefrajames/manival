/*
 * Copyright 2019 jefrajames.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jefrajames.manival;

import org.jefrajames.manival.schema.queries.AllHeroesQuery;

import java.util.List;

import static java.lang.System.out;

/**
 * @author jefrajames
 */
public class SuperHeroesClient {

    private static String SERVER_URL = "http://localhost:8080/graphql";

    public static void allHeroes() {
        AllHeroesQuery query = AllHeroesQuery.builder().build();
        AllHeroesQuery.Result result = query.request(SERVER_URL).post();


        int size = result.getAllHeroes().size();
        out.println("heroes size=" + size);


        List<AllHeroesQuery.Result.allHeroes> heroes = result.getAllHeroes();
        for (AllHeroesQuery.Result.allHeroes hero : heroes) {
            out.println("\thero.name=" + hero.getName());
            out.println("\thero realName=" + hero.getRealName());
            out.println("\thero primaryLocation=" + hero.getPrimaryLocation());
        }

    }

    public static void main(String... args) {
        allHeroes();
    }

}
