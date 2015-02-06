(ns lein-git-version.plugin
  (:use
   clojure.pprint
   leiningen.git-version))

(defn middleware
  [project]
  (let [v (get-git-version)
        b (get-git-branch)
        s (str v "::" b)]
   (-> project
       (assoc :version v)
       (assoc-in [:manifest "Implementation-Version"] s))))
