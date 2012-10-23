package math.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Boruvka implements Algo {

        public Graphe appliquer(Graphe graphe) {
                List<Arete> aretes = new ArrayList<Arete>(graphe.getAretes());
                List<Arete> l = new ArrayList<Arete>();
                // maps copy -> original
                Map<Arete, Arete> aretes_originales = new HashMap<Arete, Arete>();

                // création des arêtes
                final int mul = aretes.size() + 1;
                int i = 0;
                for (Arete a : aretes) {
                        Arete cp = new Arete(a.getU(), a.getV(), a.getPoids() * mul + i);
                        l.add(cp);
                        aretes_originales.put(cp, a);
                        ++i;
                }
                
                List<Arete> r = doBoruvka(new Graphe(l));
                Graphe result = new Graphe();
                for (Arete a : r)
                        result.ajouterArete(aretes_originales.get(a));
                return result;
        }

        private List<Arete> doBoruvka(Graphe g) {
                List<Arete> acpm = new ArrayList<Arete>();
                Set<Arete> aretes_mini = new HashSet<Arete>();
                while (g.getSommets().size() > 1) {
                        for (Integer s : g.getSommets()) {
                                // obtention de l'arete minimale
                                Arete arete_min = null;
                                for (Arete a : g.getAretesAdjacentes(s.intValue())) {
                                        if (arete_min == null
                                                        || arete_min.getPoids() > a.getPoids())
                                                arete_min = a;
                                }
                                assert (arete_min != null);
                                aretes_mini.add(arete_min);
                        }

                        for (Arete a : aretes_mini)
                                g.unir(a);

                        acpm.addAll(aretes_mini);
                        aretes_mini.clear();
                }

                return acpm;
        }

        public String getName() {
                return "Boruvka";
        }

}
