(ns next-image.core
  (:require [seesaw.core :as seesaw]))

;; left off on line 182 of https://gist.github.com/daveray/1441520 where it starts to get useful
;; see listbox example https://github.com/daveray/seesaw/blob/master/test/seesaw/test/examples/scroll.clj
(seesaw/native!)
;; list files

(def next-image-state
  "holds all state for app"
  (atom {:todo-files nil
         :done-files nil
         :todo-files-count nil
         :done-files-count nil}))

(defn update-todo-done-field!
  "updates todo and done lists in state atom.
  Also updates file lengths metadata"
  [state-atom kw thing]
  (swap! state-atom assoc kw thing)
  (swap! state-atom assoc :todo-files-count (count (:todo-files @state-atom)))
  (swap! state-atom assoc :done-files-count (count (:done-files @state-atom))))


(defn file-list
  []
  (seesaw/border-panel :north
                       (seesaw/scrollable (seesaw/listbox :model ["f1" "f2" "f3"]))
                       :south (seesaw/label "metadata here")))

(def next_image_gui
  (seesaw/frame :title "next image v0.1-dev"
                                  :size [400 :by 600]
                                  :content (file-list)))

(defn start-app
  [gui]
  (-> gui
      seesaw/show!))

(defn mod [content]
  (seesaw/config! next_image_gui
                  :content content)
  content)

(start-app next_image_gui)

#_(mod (seesaw/label "new stuff"))

