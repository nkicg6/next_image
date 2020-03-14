(ns next-image.core
  (:require [seesaw.core :as sc]))

;; left off on line 182 of https://gist.github.com/daveray/1441520 where it starts to get useful 
(sc/native!)
;; list files

(def next-image-state
  "holds all state for app"
  (atom {:todo-files nil
         :done-files nil
         :files-remaining nil
         :files-done nil}))

(defn update-todo-done-field!
  "updates todo adn done lists. Also updates metadata like file lengths"
  [state-atom kw thing]
  (swap! state-atom assoc kw thing)
  (swap! state-atom assoc :files-remaining (count (:done-files @state-atom)))
  (swap! state-atom assoc :files-done (count (:todo-files @state-atom))))

;;;; repl play and development

(defn add-test-files [v]
   v)

(update-todo-done-field! next-image-state :todo-files (add-test-files ["f1" "f2" "f3"]))
(update-todo-done-field! next-image-state :done-files (add-test-files ["done1" "done2"]))

@next-image-state

