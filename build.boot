
(set-env!
  :resource-paths #{"src"}
  :dependencies '[])

(def +version+ "0.1.2")

(deftask build []
  (comp
    (pom :project     'cirru/bisection-key
         :version     +version+
         :description "Ordered string key for maps"
         :url         "https://github.com/Cirru/bisection-key"
         :scm         {:url "https://github.com/Cirru/bisection-key"}
         :license     {"MIT" "http://opensource.org/licenses/mit-license.php"})
    (jar)
    (install)
    (target)))

(deftask deploy []
  (set-env!
    :repositories #(conj % ["clojars" {:url "https://clojars.org/repo/"}]))
  (comp
    (build)
    (push :repo "clojars" :gpg-sign (not (.endsWith +version+ "-SNAPSHOT")))))
