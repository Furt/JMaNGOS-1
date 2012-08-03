/*******************************************************************************
 * Copyright (c) 2012 JMANGOS
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     JMANGOS - initial API and implementation
 ******************************************************************************/
/*
 * Copyright (C) 2011 WoWEmu This program is free software; you can redistribute
 * it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the License,
 * or (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details. You should have received a
 * copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.jmangos.auth.service;

import javax.inject.Inject;

import org.jmangos.auth.config.Config;
import org.jmangos.commons.service.Service;
import org.jmangos.commons.task.TaskId;
import org.jmangos.commons.task.TaskManager;
import org.jmangos.commons.task.TaskPriority;
import org.jmangos.commons.threadpool.ThreadPoolManager;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateService.
 */
public class UpdateService implements Service {
	
	/** The task manager. */
	private TaskManager taskManager = new TaskManager();
	
	/** The thread pool manager. */
	@Inject
	private ThreadPoolManager threadPoolManager;
	
	/** The world list service. */
	@Inject
	private WorldListService worldListService;

	/**
	 * The Enum UpdateWorldTaskId.
	 */
	private static enum UpdateWorldTaskId implements TaskId {
		
		/** The L s_ worl d_ update. */
		LS_WORLD_UPDATE;

		/* (non-Javadoc)
		 * @see org.wowemu.common.task.TaskId#getPriority()
		 */
		@Override
		public TaskPriority getPriority() {
			return TaskPriority.NORMAL;
		}

	}

	/* (non-Javadoc)
	 * @see org.wowemu.common.service.Service#start()
	 */
	@Override
	public void start() {
			taskManager.addNewTask(UpdateWorldTaskId.LS_WORLD_UPDATE,
					threadPoolManager.scheduleAtFixedRate(
							new LUpdateWorldList(),
							Config.UPDATE_INTERVAL * 1000,
							Config.UPDATE_INTERVAL * 1000));
	}

	/* (non-Javadoc)
	 * @see org.wowemu.common.service.Service#stop()
	 */
	@Override
	public void stop() {
		taskManager.cancelAllTasks();
	}

	/**
	 * The Class LUpdateWorldList.
	 */
	private final class LUpdateWorldList implements Runnable {
		
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			worldListService.reload();
		}
	}
}