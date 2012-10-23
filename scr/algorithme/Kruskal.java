package algorithme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Kruskal implements Algorithme {
        public Graphe appliquer(Graphe g){
		List<Arete> aretes = g.getAretes();
                Collections.sort(aretes, Collections.reverseOrder());
                UnionFind uf = new UnionFind(g.nbAretes());
                
                return new Graphe(doKruskal(uf, aretes));
        }
        
        private List<Arete> doKruskal(UnionFind uf, List<Arete> E) {
                List<Arete> result = new ArrayList<Arete>();
                for(Arete a : E) {
                        int up = uf.find(a.getU());
                        int vp = uf.find(a.getV());
                        if (up != vp) {
                                result.add(a);
                                uf.link(up, vp);
                        }
                }
                return result;
        }

        public String getName() {
                return "Kruskal";
        }
}
