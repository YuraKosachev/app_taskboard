ALTER TABLE tasks
ADD CONSTRAINT FK_tasks_userId FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE;