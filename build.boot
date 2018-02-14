(defn read-password [guide]
  (String/valueOf (.readPassword (System/console) guide nil)))

(set-env!
  :resource-paths #{"src"}
  :dependencies '[]
  :repositories #(conj % ["clojars" {:url "https://clojars.org/repo/"
                                     :username "jiyinyiyong"
                                     :password (read-password "Clojars password: ")}]))

(def +version+ "0.1.4")

(deftask deploy []
  (comp
    (pom :project     'cirru/bisection-key
         :version     +version+
         :description "Ordered string key for maps"
         :url         "https://github.com/Cirru/bisection-key"
         :scm         {:url "https://github.com/Cirru/bisection-key"}
         :license     {"MIT" "http://opensource.org/licenses/mit-license.php"})
    (jar)
    (install)
    (push :repo "clojars" :gpg-sign false)))
